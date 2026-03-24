package com.houserent.service;

import com.houserent.domain.House;

/**
 * HouseSercive.java<=>类[业务层]
 * 1.响应HouseView的调用
 * 2.完成对房屋信息的各种操作(增删改查crud)
 */
public class HouseSercive {

    private House[] houses;//保存House对象
    private int HouseNums = 1;//记录当前有多少个房屋信息
    private int idCounter = 1;//记录当前的id增长到哪个值
    //构造器
    public HouseSercive(int size){
        //new houses
        houses = new House[size];//当创建House对象时指定数组大小
        //测试列表信息，先初始化一个House对象
        houses[0] = new House(1,"jack","181",
                "海珠区",2500,"未出租");
    }


    //check方法，输出一个房屋信息
    public House check(int checkId){
        for (int i = 0; i < HouseNums; i++) {
            if (checkId == houses[i].getId()) {//通过遍历得到要查找的房屋的数组下标
                return houses[i];//直接返回房屋信息，默认返回toString方法
            }
        }
        return null;

    }
    //del方法，删除一个房屋信息
    public boolean del(int delId){
        int index = -1;
        //通过遍历找到需要删除的房屋所在数组的下标
        for (int i = 0; i < HouseNums; i++) {
            if(delId == houses[i].getId()){//要删除房屋的id的数组下标为i
                index = i;
            }
        }
        if(index == -1){//说明delId在数组中不存在
            return false;
        }
        //如果找到，则需将下标为i之后的每一个数组往前移一个单位
        for (int i = index; i < HouseNums - 1; i++) {
            houses[i] = houses[i + 1];
        }
        houses[--HouseNums] = null;//将最后一个数组赋值为空并相对应的减少房屋数
        /*for (int i = 0; i < HouseNums; i++) {
            houses[i].setId(i + 1);
        }*/
        return true;
    }
    //add方法，添加新对象，返回boolean
    public boolean add(House newhouse){
        //判断是否可以继续添加(若不能则将数组进行扩容)
        if(HouseNums == houses.length){
            House[] housesNew = new House[houses.length+1];
            for (int i = 0; i < houses.length; i++) {
                housesNew[i] = houses[i];
            }
            housesNew[houses.length] = newhouse;
            houses = housesNew;
        }
        //把newHouse对象加入到数组中
        houses[HouseNums++] = newhouse;
        //设计一个id自增长机制，更新newHouse的id
        newhouse.setId(++idCounter);
        return true;
    }
    //list方法，返回houses
    public House[] list(){
        return houses;
    }

    public House getHouses(int i) {
        return houses[i];
    }

    public void setHouses(House[] houses) {
        this.houses = houses;
    }
}
