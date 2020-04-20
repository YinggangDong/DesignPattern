package cn.dyg.templatepattern.service;

import cn.dyg.templatepattern.domain.assessment.ItemParam;
import cn.hutool.core.util.RandomUtil;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MissCheckRate类(接口)是 漏检率考核类
 *
 * @author dongyinggang
 * @date 2020-04-19 14:24
 **/
public class MissCheckRateService extends AbstractRateTemplate {
    /**
     * getItemParam方法是 获取考核项（漏检率）的参数
     *
     * @return ItemParam 参数列表
     * @author dongyinggang
     * @date 2020/4/16 16:05
     */
    @Override
    List<ItemParam> getItemParam() {
        //模拟从数据库获取到漏检率考核信息列表
        List<ItemParam> itemParamList = new ArrayList<>();
        ItemParam itemParamA = new ItemParam("漏检率", "A", 12, 4, 0);
        ItemParam itemParamB = new ItemParam("漏检率", "B", 32, 3, 0);
        ItemParam itemParamC = new ItemParam("漏检率", "C", 30, 2, 0);
        ItemParam itemParamD = new ItemParam("漏检率", "D", 26, 1, 0);
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
        List<Double> missCheckRateList = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            missCheckRateList.add(RandomUtil.randomDouble(0, 30, 2, RoundingMode.HALF_DOWN));
        }
        //模拟出现重复的情况
        for (int i = 0; i < 5; i++) {
            missCheckRateList.add(23.33);
            missCheckRateList.add(3.22);
            missCheckRateList.add(1.55);
            missCheckRateList.add(10.50);
        }
        //升序排列
        Collections.sort(missCheckRateList);
        //通过迭代器输出所有随机生成的元素,ide提示使用foreach而非while,存疑
//        Iterator it = missCheckRateList.iterator();
//        while (it.hasNext()) {
//            System.out.print(it.next() + ",");
//        }
//        System.out.println();
//        //通过foreach输出
//        for (Double missCheckRate : missCheckRateList) {
//            System.out.print(missCheckRate + ",");
//        }
//        System.out.println();
        System.out.println("漏检率原始数据如下：\n" + missCheckRateList.toString());

        return missCheckRateList;
    }

    /**
     * calInterval方法是 计算区间,计算的是降序list的区间(考核项值越大越好)
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
        return CommonService.calIntervalAsc(itemParam, assessmentList, index, rangeIndex);
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
//    public List<AssessmentResultDto> getAssessmentResult(List<ItemParam> itemParamList) {
//        List<AssessmentResultDto> assessmentList = new ArrayList<>();
//
//        //获取随机生成的考核值数组
//        List<Double> missCheckList = getAssessmentValue();
//        //如果为空,直接返回空list
//        if (CollectionUtil.isEmpty(missCheckList)) {
//            return assessmentList;
//        }
//        //记录当前已经遍历到有序数组的哪个位置
//        int index = 0;
//        //获取每个等级的人数
//        for (ItemParam itemParam : itemParamList) {
//            //根据总人数设置当前等级人数的理论值
//            itemParam.setCurrentGradePersonNum(missCheckList.size());
//            //设置当前等级的统计结果,并返回进行到了哪一个下标
//            index = getRangeIndex(itemParam, assessmentList, missCheckList, index);
//            //如果的已经到了数组的最后,则考核项结果已经全部生成,进行return,不再继续循环
//            if (index == missCheckList.size() - 1) {
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
//     * @param missCheckList 存储考核项值的数组
//     * @param index 上一个等级进行到了哪个下标
//     * @return  当前等级进行到了哪个下标
//     * @author dongyinggang
//     * @date 2020/4/19 13:57
//     */
//    private int getRangeIndex(ItemParam itemParam, List<AssessmentResultDto> assessmentList,
//                              List<Double> missCheckList, int index) {
//
//        //前一个等级的最后位置+当前等级的人数就是本等级最终理论应该达到的位置,但如果已经超出数组,则将其赋值为数组最后一位
//        int rangeIndex = itemParam.getPersonNum() + index > missCheckList.size() - 1 ?
//                missCheckList.size() - 1 : itemParam.getPersonNum() + index;
//        String interval = CommonService.calIntervalAsc(itemParam, missCheckList, index,rangeIndex);
//
//
//        //获取到当前等级的实际最终位置,如果界限出现相等值，会全部算作更高的考核级别
//        int actualRangeIndex = missCheckList.lastIndexOf(missCheckList.get(rangeIndex));
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
