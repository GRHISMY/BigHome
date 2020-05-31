package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.enpity.BuyerSellerInfo;
import com.example.demo.enpity.GoodsInfo;
import com.example.demo.enpity.RecommendGoods;
import com.example.demo.mapper.BuyerSellerInfoMapper;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.service.RecommendServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class RecommendServerImpl implements RecommendServer {
    @Autowired
    BuyerSellerInfoMapper buyerSellerInfoMapper;
    @Autowired
    GoodsInfoMapper goodsInfoMapper;

    //相似用户集合
    private static List<List<Object>> similarity=null;
    //推荐所有电影集合
    private static List<GoodsInfo> targetRecommend=null;

    //点评过电影集合
    private static List<GoodsInfo> commented=null;

    @Override
    public void addFootPrint(Integer userId, Integer goodsId) {
        RecommendGoods recommendGoods = new RecommendGoods();

        ArrayList<RecommendGoods> recommendGoodsArrayList = (ArrayList<RecommendGoods>) recommendGoodsList();
        // 1首先找到有没有这个用户
        Integer userIndex = findUser(userId,recommendGoodsArrayList);
        if (userIndex == -1){// 1。1 如果没有这个用户 那么添加这个用户 和商品记录

            Map<Integer,Integer> goodsIdAndFootprintfNumber = new HashMap<Integer,Integer>();
            goodsIdAndFootprintfNumber.put(goodsId,1);

            recommendGoods.setUserId(userId);
            recommendGoods.setGoodsIdAndFootprintfNumber(goodsIdAndFootprintfNumber);

            recommendGoodsArrayList.add(recommendGoods);

            list2Redis(recommendGoodsArrayList);

        }else {// 1。1 如果有这个用户 那么我就找有没有这个商品 有我就+1没有就添加商品
            Map<Integer,Integer> map = recommendGoodsArrayList.get(userIndex).getGoodsIdAndFootprintfNumber();
            if (map.containsKey(goodsId)){
                map.put(goodsId,map.get(goodsId)+1);
            }else {
                map.put(goodsId,1);
            }

            recommendGoodsArrayList.get(userIndex).setGoodsIdAndFootprintfNumber(map);
            list2Redis(recommendGoodsArrayList);
        }


    }

    @Override
    public Integer recommendGoodsId(Integer userId) {
        List<BuyerSellerInfo> buyerSellerInfoList = buyerSellerInfoMapper.findAll();
        List<GoodsInfo> goodsList = goodsInfoMapper.getGoodsList();

        int line = buyerSellerInfoList.size();
        int col = goodsList.size();

        int[][] allFootPrint = new int[line][col];

        List<RecommendGoods> recommendGoodsList = recommendGoodsList();
//        for (int i = 0; i < allFootPrint.length; i++){
//            for(int j = 0; j < allFootPrint[i].length; j++){
//
//            }
//        }
        for (RecommendGoods recommendGoods : recommendGoodsList){
            Integer userId1 = recommendGoods.getUserId();
            Integer userIndex = findUserId(userId1,buyerSellerInfoList);
            for (Map.Entry<Integer, Integer> entry : recommendGoods.getGoodsIdAndFootprintfNumber().entrySet()) {
                Integer goodsIndex = findGoodsId(entry.getKey(),goodsList);
                if (goodsIndex == -1){
                    continue;
                }else {
                    allFootPrint[userIndex][goodsIndex] = entry.getValue();
                }
            }
        }
        // 最后返回的下标要转回id
        Integer uIndex = findUserId(userId,buyerSellerInfoList);
        int userLength = buyerSellerInfoList.size();
        calcUserSimilarity(allFootPrint,uIndex,userLength);
        System.err.println(similarity);

        int goodsLength = goodsList.size();

        calcRecommendMovie(allFootPrint,goodsLength,goodsList);
        System.err.println(targetRecommend);

        handleRecommend(allFootPrint,uIndex,goodsList);
        System.err.println(commented);

        System.out.print("推荐电影列表：");
        for (GoodsInfo item:targetRecommend){
            if (commented == null){
                break;
            }
            if(!commented.contains(item)){
                System.out.print(item+"  ");
                return item.getGoods_id();
            }
        }




        for (int[] is : allFootPrint) {
            for (int i : is) {
                System.out.print(i + ",");
            }
            System.out.println();
        }


            return -1;
    }

    public Integer findUser(Integer userId,ArrayList<RecommendGoods> recommendGoodsArrayList){
        Integer flage = -1;
        Integer index = 0;
        for(RecommendGoods recommendGoods1 : recommendGoodsArrayList){
            if (recommendGoods1.getUserId() == userId){
                flage = index;
            }
            index++;
        }
        return flage;
    }

    public Integer findUserId(Integer userId,List<BuyerSellerInfo> buyerSellerInfoList){
        Integer flage = -1;
        Integer index = 0;
        for(BuyerSellerInfo buyerSellerInfo : buyerSellerInfoList){
            if (buyerSellerInfo.getbSId() == userId){
                flage = index;
            }
            index++;
        }
        return flage;
    }

    public Integer findGoodsId(Integer goodsId,List<GoodsInfo> goodsList){
        Integer flage = -1;
        Integer index = 0;
        for(GoodsInfo goodsInfo : goodsList){
            if (goodsInfo.getGoods_id() == goodsId){
                flage = index;
            }
            index++;
        }
        return flage;
    }


    public List<RecommendGoods> recommendGoodsList() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        ArrayList<RecommendGoods> recommendGoodsArrayList = (ArrayList<RecommendGoods>) JSONObject.parseArray(jedis.get("recommendGoodsArrayList"),RecommendGoods.class);
        jedis.close();

        if (recommendGoodsArrayList == null || recommendGoodsArrayList.size() == 0){
            recommendGoodsArrayList = new ArrayList<RecommendGoods>();
        }

        return recommendGoodsArrayList;

    }

    public void list2Redis(ArrayList<RecommendGoods> userList){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("recommendGoodsArrayList", String.valueOf(JSONArray.parseArray(JSON.toJSONString(userList))));
        jedis.close();

    }

    public void printredis(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        ArrayList<RecommendGoods> recommendGoodsArrayList = (ArrayList<RecommendGoods>) JSONObject.parseArray(jedis.get("recommendGoodsArrayList"),RecommendGoods.class);
        jedis.close();

        if (recommendGoodsArrayList == null || recommendGoodsArrayList.size() == 0){
            recommendGoodsArrayList = new ArrayList<RecommendGoods>();
        }

        System.err.println(recommendGoodsArrayList);
    }
    /**
     * 获取两个最相似的用户
     */
     public void calcUserSimilarity(int[][] allFootPrint,Integer targetUserIndex,int userLength){
        similarity=new ArrayList<>();
        List<List<Object>> userSimilaritys=new ArrayList<>();

        for (int i=0;i<userLength;i++){
            if(i==targetUserIndex){
                continue;
            }
            List<Object> userSimilarity=new ArrayList<>();
            userSimilarity.add(i);
            userSimilarity.add(calcTwoUserSimilarity(allFootPrint[i],allFootPrint[targetUserIndex]));
            userSimilaritys.add(userSimilarity);
        }

        sortCollection(userSimilaritys,1);

        similarity.add(userSimilaritys.get(0));
        similarity.add(userSimilaritys.get(1));

    }

    /**
     * 获取全部推荐电影,计算平均电影推荐度
     */
    private static void calcRecommendMovie(int[][] allFootPrint,int length,List<GoodsInfo> goodsList){
        targetRecommend=new ArrayList<GoodsInfo>();
        List<List<Object>> recommendMovies=new ArrayList<>();
        List<Object> recommendMovie=null;
        double recommdRate=0,sumRate=0;
        for (int i=0;i<length;i++){
            recommendMovie=new ArrayList<>();
            recommendMovie.add(i);
            recommdRate=allFootPrint[Integer.parseInt(similarity.get(0).get(0).toString())][i]*Double.parseDouble(similarity.get(0).get(1).toString())
                    +allFootPrint[Integer.parseInt(similarity.get(1).get(0).toString())][i]*Double.parseDouble(similarity.get(1).get(1).toString());
            recommendMovie.add(recommdRate);
            recommendMovies.add(recommendMovie);
            sumRate+=recommdRate;
        }

        sortCollection(recommendMovies,-1);

        for (List<Object> item:recommendMovies){
            if(Double.parseDouble(item.get(1).toString()) > sumRate/7){ //大于平均推荐度的商品才有可能被推荐
//                targetRecommend.add(movies[Integer.parseInt(item.get(0).toString())]);
                targetRecommend.add(goodsList.get((Integer) item.get(0)));
            }
        }
    }

    /**
     * 把推荐列表中用户已经点评过的电影剔除
     */
    private static void handleRecommend(int[][] allFootPrint,Integer targetUserIndex,List<GoodsInfo> goodsList){
        commented=new ArrayList<GoodsInfo>();
        for (int i=0;i<allFootPrint[targetUserIndex].length;i++){
            if(allFootPrint[targetUserIndex][i]!=0){
                commented.add(goodsList.get(i));
            }
        }
    }

    /**
     * 根据用户数据，计算用户相似度
     * @param user1Stars
     * @param user2Starts
     * @return
     */
    private static double calcTwoUserSimilarity(int[] user1Stars,int[] user2Starts){
        float sum=0;
        for(int i=0;i<7;i++){
            sum+=Math.pow(user1Stars[i]-user2Starts[i],2);
        }
        return Math.sqrt(sum);
    }

    /**
     * 集合排序
     * @param list
     * @param order  1正序 -1倒序
     */
    private static void sortCollection(List<List<Object>> list,int order){
        Collections.sort(list, new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                if(Double.valueOf(o1.get(1).toString()) > Double.valueOf(o2.get(1).toString())){
                    return order;
                }else if(Double.valueOf(o1.get(1).toString()) < Double.valueOf(o2.get(1).toString())){
                    return -order;
                }else{
                    return 0;
                }
            }
        });
    }
}
