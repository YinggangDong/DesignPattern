package cn.dyg.templatepattern.service;

import cn.dyg.templatepattern.domain.assessment.ItemParam;
import cn.hutool.core.util.RandomUtil;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ReworkRateService类(接口)是 开发返工率Service
 *
 * @author dongyinggang
 * @date 2020-04-19 16:42
 **/
public class ReworkRateService extends AbstractRateTemplate{
    /**
     * getItemParam方法是 获取考核项(开发返工率)的参数
     *
     * @return ItemParam 参数列表
     * @author dongyinggang
     * @date 2020/4/16 16:05
     */
    @Override
    List<ItemParam> getItemParam() {
        //模拟从数据库获取到用例数量考核信息列表
        List<ItemParam> itemParamList = new ArrayList<>();
        ItemParam itemParamA = new ItemParam("开发返工率", "A", 29, 4, 0);
        ItemParam itemParamB = new ItemParam("开发返工率", "B", 35, 3, 0);
        ItemParam itemParamC = new ItemParam("开发返工率", "C", 32, 2, 0);
        ItemParam itemParamD = new ItemParam("开发返工率", "D", 4, 1, 0);
        itemParamList.add(itemParamA);
        itemParamList.add(itemParamB);
        itemParamList.add(itemParamC);
        itemParamList.add(itemParamD);
        return itemParamList;
    }

    /**
     * getAssessmentValue方法是 获取随机生成的考核值数组
     *
     * @return 倒序排列的考核值数组
     * @author dongyinggang
     * @date 2020/4/17 15:33
     */
    @Override
    public List<Double> getAssessmentValue() {
        //模拟数据库查询的用例数的数组
        List<Double> reworkRateList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            reworkRateList.add(RandomUtil.randomDouble(0, 80,2, RoundingMode.HALF_DOWN));
        }
        //模拟出现重复的情况
        for (int i = 0; i < 5; i++) {
            reworkRateList.add(23.33);
            reworkRateList.add(5.22);
            reworkRateList.add(0.33);
            reworkRateList.add(0.20);
        }
        //升序排列
        Collections.sort(reworkRateList);
        System.out.println("开发返工率原始数据如下：\n" + reworkRateList.toString());

        return reworkRateList;
    }

    /**
     * calInterval方法是 计算区间
     * 具体计算的是降序list的区间(考核项值越大越好),还是升序list的区间(考核项值越小越好)由实现类自己决定
     *
     * @param itemParam      考核项基础信息
     * @param assessmentList 考核项值的升序list
     * @param index          上一个等级结束的位置
     * @param rangeIndex     当前等级的结束位置
     * @return 计算得到的区间值
     * @author dongyinggang
     * @date 2020/4/19 15:43
     */
    @Override
    String calInterval(ItemParam itemParam, List<Double> assessmentList, int index, int rangeIndex) {
        return CommonService.calIntervalAsc(itemParam, assessmentList, index,rangeIndex);
    }

    /**
     * intervalHock方法是 钩子方法,子类可以覆盖这个方法做一定处理(但非必须),并依此做到控制模板流程
     *
     * 在这里通过返回不同desc或asc来控制计算区间调用的方法
     *
     * @author dongyinggang
     * @date 2020/4/16 18:33
     */
    @Override
    String intervalHock() {
        return "asc";
    }

    //    /**
//     * getAssessmentResult方法是 根据参数得到考核结果
//     *
//     * @param itemParamList 考核项的可配置参数
//     * @return 考核结果
//     * @author dongyinggang
//     * @date 2020/4/16 16:10
//     */
//    @Override
//    List<AssessmentResultDto> getAssessmentResult(List<ItemParam> itemParamList) {
//        List<AssessmentResultDto> assessmentList = new ArrayList<>();
//
//        //获取随机生成的考核值数组
//        List<Double> reworkRateList = getAssessmentValue();
//        //如果为空,直接返回空list
//        if (CollectionUtil.isEmpty(reworkRateList)) {
//            return assessmentList;
//        }
//        //记录当前已经遍历到有序数组的哪个位置
//        int index = 0;
//        //获取每个等级的人数
//        for (ItemParam itemParam : itemParamList) {
//            //根据总人数设置当前等级人数的理论值
//            itemParam.setCurrentGradePersonNum(reworkRateList.size());
//            //设置当前等级的统计结果,并返回进行到了哪一个下标
//            index = getRangeIndex(itemParam, assessmentList, reworkRateList, index);
//            //如果的已经到了数组的最后,则考核项结果已经全部生成,进行return,不再继续循环
//            if (index == reworkRateList.size() - 1) {
//                return assessmentList;
//            }
//        }
//
//        return assessmentList;
//    }

//    /**
//     * getRangeIndex方法是 设置当前等级的统计结果,并返回进行到了哪一个下标
//     *
//     * @param itemParam 当前等级的初始信息
//     * @param assessmentList 最终返回的list结果
//     * @param reworkRateList 存储考核项值的数组
//     * @param index 上一个等级进行到了哪个下标
//     * @return  当前等级进行到了哪个下标
//     * @author dongyinggang
//     * @date 2020/4/19 13:57
//     */
//    private int getRangeIndex(ItemParam itemParam, List<AssessmentResultDto> assessmentList,
//                              List<Double> reworkRateList, int index) {
//
//        //前一个等级的最后位置+当前等级的人数就是本等级最终理论应该达到的位置,但如果已经超出数组,则将其赋值为数组最后一位
//        int rangeIndex = itemParam.getPersonNum() + index > reworkRateList.size() - 1 ?
//                reworkRateList.size() - 1 : itemParam.getPersonNum() + index;
//        String interval = CommonService.calIntervalAsc(itemParam, reworkRateList, index,rangeIndex);
//
//        //获取到当前等级的实际最终位置,如果界限出现相等值，会全部算作更高的考核级别
//        int actualRangeIndex = reworkRateList.lastIndexOf(reworkRateList.get(rangeIndex));
//
//        //人数是实际最终下标-开始下标,如果是A的话,因为从0开始,要进行+1
//        int personNum = actualRangeIndex - index;
//        if ("A".equals(itemParam.getGrade())) {
//            personNum++;
//        }
//        CommonService.setGradeInfo(itemParam, assessmentList, interval, personNum);
//
//        return actualRangeIndex;
//    }
}
