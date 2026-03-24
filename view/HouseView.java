package com.houserent.view;

import com.houserent.domain.House;
import com.houserent.service.HouseSercive;
import com.houserent.utils.Utility;

import javax.rmi.CORBA.Util;

/**
 * 1.显示界面
 * 2.接受用户的输入
 * 3.调用HouseService完成对房屋信息的各种操作
 */
public class HouseView {
    private boolean loop = true;//控制显示菜单
    private char key = ' ';//接收用户选择
    private HouseSercive houseSercive = new HouseSercive(2);//设置数组的大小为10
    private House house;
    //编写updateHouse(),接收输入的房屋编号，调用Sercive的check方法
    public void updateHouses(){
        System.out.println("=================修改房屋===================");
        System.out.print("请选择待修改房屋编号(输入-1退出):");
        int updateId = Utility.readInt();
        if(updateId == -1){
            System.out.println("================放弃修改房屋信息=================");
            return;
        }
        House house = houseSercive.check(updateId);//返回需要修改房屋的原始信息
        if(house == null){
            System.out.println("==================修改房屋信息不存在===================");
            return;
        }
        System.out.print("姓名("+house.getName()+"): ");
        String name = Utility.readString(8,"");//这里如果用户直接回车表示不修改该信息，默认""
        if(!"".equals(name)){//修改
            house.setName(name);
        }
        System.out.print("电话("+house.getPhone()+"): ");
        String phone = Utility.readString(12,"");
        if(!"".equals(phone)){
            house.setPhone(phone);
        }
        System.out.print("地址("+house.getAddress()+"): ");
        String address = Utility.readString(16,"");
        if(!"".equals(address)){
            house.setAddress(address);
        }
        System.out.print("租金("+house.getRent()+"): ");
        int rent = Utility.readInt(-1);
        if(!(-1 == rent)){
            house.setRent(rent);
        }
        System.out.print("状态("+house.getState()+"): ");
        String state = Utility.readString(3,"");
        if(!"".equals(state)){
            house.setState(state);
        }
            System.out.println("==================修改房屋信息成功==================");
    }
    //编写checkHouse()，接收输入的房屋编号，调用Sercive的check方法
    public void checkHouses(){
        System.out.println("===================查找房屋====================");
        System.out.print("请输入你要查找的房屋编号:");
        int checkId = Utility.readInt();
        houseSercive.check(checkId);
    }
    //完成退出确认功能
    public void exit(){
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y'){
            loop = false;
        }else{
            System.out.println("您已返回房屋出租App");
        }
    }
    //编写delHouse()，接收输入的id，调用Sercive的del方法
    public void delHouse() {
        System.out.println("=================删除房屋信息=================");
        System.out.print("请输入待删除的房屋id(输入-1退出):");
        int delId = Utility.readInt();
        if(delId == -1){
            System.out.println("================放弃删除房屋信息=================");
            return;
        }
        //调用Utility中的方法(本身就有循环判断的逻辑)
        char choice = Utility.readConfirmSelection();
        if(choice == 'Y'){//真的删除
            if(houseSercive.del(delId)){
                System.out.println("================删除房屋信息成功=================");
            }else{
                System.out.println("================房屋编号不存在，删除失败=================");
            }
        }else{
            System.out.println("================放弃删除房屋信息=================");
        }
    }
    //编写addHouse()接收输入，创建House对象，调用add方法
    public void addHouse(){
        System.out.println("=================添加房屋===================");
        System.out.print("姓名: ");
        String name = Utility.readString(8);
        System.out.print("电话: ");
        String phone = Utility.readString(12);
        System.out.print("地址: ");
        String address = Utility.readString(16);
        System.out.print("月租: ");
        int rent = Utility.readInt();
        System.out.print("状态: ");
        String state = Utility.readString(3);
        //创建一个新的House对象，注意id是系统分配的，
        House newhouse = new House(0,name,phone,address,rent,state);
        if(houseSercive.add(newhouse)){
            System.out.println("==================添加房屋成功==================");
        }else{
            System.out.println("==================添加房屋失败===================");
        }
    }
    //编写listHouses()显示房屋列表
    public void listHouses(){
        System.out.println("===================房屋列表===================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseSercive.list();//得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if(houses[i] == null){
                continue;
            }
            else{
                System.out.println(houses[i]);//默认调用toString方法打印房屋出租信息
            }
        }
        System.out.println("====================房屋列表显示完毕====================");
    }
    //显示主菜单
    public void mainMenu(){
        do{
            System.out.println("---------------房屋出租系统---------------");
            System.out.println("\t\t\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t\t\t2 查 找 房 源");
            System.out.println("\t\t\t\t\t3 删 除 房 源");
            System.out.println("\t\t\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t\t\t5 房 屋 列 表");
            System.out.println("\t\t\t\t\t6 退      出");
            System.out.println("请输入你的选择(1-6)");
            key = Utility.readChar();

            switch (key){
                case '1':
                    this.addHouse();
                    break;
                case '2':
                    this.checkHouses();
                    break;
                case '3':
                    this.delHouse();
                    break;
                case '4':
                    this.updateHouses();
                    break;
                case '5':
                    this.listHouses();
                    break;
                case '6':
                    this.exit();
                    break;
            }
        }while(loop);
    }
}
