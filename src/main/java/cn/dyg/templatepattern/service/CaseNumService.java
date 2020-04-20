package cn.dyg.templatepattern.service;

import cn.dyg.templatepattern.domain.assessment.AssessmentResultDto;
import cn.dyg.templatepattern.domain.assessment.ItemParam;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HttpClient->CaseNumService 用例数考核类
 *
 * @author dongyinggang
 * @date 2020-04-16 16:27
 **/
public class CaseNumService extends BaseAssessmentTemplate {

    /**
     * getItemParam方法是 获取考核项的参数
     *
     * @return ItemParam 考核项参数
     * @author dongyinggang
     * @date 2020/4/16 16:05
     */
    @Override
    List<ItemParam> getItemParam() {
        //模拟从数据库获取到用例数量考核信息列表
        List<ItemParam> itemParamList = new ArrayList<>();
        ItemParam itemParamA = new ItemParam("用例数量", "A", 14, 4, 0);
        ItemParam itemParamB = new ItemParam("用例数量", "B", 16, 3, 0);
        ItemParam itemParamC = new ItemParam("用例数量", "C", 16, 2, 0);
        ItemParam itemParamD = new ItemParam("用例数量", "D", 54, 1, 0);
        itemParamList.add(itemParamA);
        itemParamList.add(itemParamB);
        itemParamList.add(itemParamC);
        itemParamList.add(itemParamD);
        return itemParamList;
    }

    /**
     * getAssessmentResult方法是 根据参数得到考核结果
     *
     * @param itemParamList 考核项的可配置参数列表
     * @return 考核结果
     * @author dongyinggang
     * @date 2020/4/16 16:10
     */
    @Override
    List<AssessmentResultDto> getAssessmentResult(List<ItemParam> itemParamList) {

        List<AssessmentResultDto> assessmentList = new ArrayList<>();

        //获取随机生成的考核值数组
        Integer[] caseNumArray = getAssessmentValue();
        //如果为空,直接返回空list
        if(ArrayUtil.isEmpty(caseNumArray)){
            return assessmentList;
        }
        //记录当前已经遍历到有序数组的哪个位置
        int index = 0;
        //获取每个等级的人数
        for (ItemParam itemParam : itemParamList) {
            //根据总人数设置当前等级人数的理论值
            itemParam.setCurrentGradePersonNum(caseNumArray.length);
            //设置当前等级的统计结果,并返回进行到了哪一个下标
            index = getRangeIndex(itemParam, assessmentList, caseNumArray, index);
            //如果的已经到了数组的最后,则考核项结果已经全部生成,进行return,不再继续循环
            if (index == caseNumArray.length - 1) {
                return assessmentList;
            }
        }

        return assessmentList;
    }

    /**
     * getRangeIndex方法是 设置当前等级的统计结果,并返回进行到了哪一个下标
     *
     * @param itemParam 当前等级的初始信息
     * @param assessmentList 最终返回的list结果
     * @param caseNumArray 存储考核项值的数组
     * @param index 上一个等级进行到了哪个下标
     * @return  当前等级进行到了哪个下标
     * @author dongyinggang
     * @date 2020/4/19 13:57
     */
    private int getRangeIndex(ItemParam itemParam, List<AssessmentResultDto> assessmentList,
                              Integer[] caseNumArray, int index) {

        //前一个等级的最后位置+当前等级的人数就是本等级最终理论应该达到的位置,但如果已经超出数组,则将其赋值为数组最后一位
        int rangeIndex = itemParam.getPersonNum() + index > caseNumArray.length - 1 ?
                caseNumArray.length - 1 : itemParam.getPersonNum() + index;
        String interval = calIntervalIntDesc(itemParam, caseNumArray, index, rangeIndex);


        //获取到当前等级的实际最终位置,如果界限出现相等值，会全部算作更高的考核级别
        int actualRangeIndex = ArrayUtil.lastIndexOf(caseNumArray, caseNumArray[rangeIndex]);

        //人数是实际最终下标-开始下标,如果是A的话,因为从0开始,要进行+1
        int personNum = actualRangeIndex - index;
        if ("A".equals(itemParam.getGrade())) {
            personNum++;
        }

        //根据计算结果设置当前grade的属性
        CommonService.setGradeInfo(itemParam, assessmentList,interval,personNum);

        return actualRangeIndex;
    }

    /**
     * calIntervalIntDesc方法是 计算区间,计算的是降序list的区间(考核项值越大越好)
     *
     * @param itemParam 考核项基础信息
     * @param caseNumArray 考核项值的升序list
     * @param index 上一个等级结束的位置
     * @param rangeIndex 当前等级的结束位置
     * @return 计算得到的区间值
     * @author dongyinggang
     * @date 2020/4/19 15:43
     */
    private String calIntervalIntDesc(ItemParam itemParam, Integer[] caseNumArray, int index, int rangeIndex) {
        //定义区间默认值"~"代表当前值
        String interval = "~";
        //获取各等级的上下限,如果不是A,则说明用例数量有右值
        if (!"A".equals(itemParam.getGrade())) {
            //右值即被小于等于的那个数,是更高等级边界值-1
            int rightValue = caseNumArray[index] - 1;
            interval += "<=" + rightValue;
        }

        //如果不是D,则说明用例数量有左值
        if (!"D".equals(itemParam.getGrade())) {
            //左值即被大于的那个数,是当前等级的边界值-1
            int leftValue = caseNumArray[rangeIndex] - 1;
            interval = leftValue + "<" + interval;
        }
        return interval;
    }

    /**
     * getAssessmentValue方法是 获取随机生成的考核值数组
     *
     * @return 倒序排列的考核值数组
     * @author dongyinggang
     * @date 2020/4/17 15:33
     */
    private Integer[] getAssessmentValue() {
        //模拟数据库查询的用例数的数组
        int[] caseNumArray = NumberUtil.generateRandomNumber(1, 200, 60);
        // Arrays.stream(arr) 可以替换成IntStream.of(arr)。
        // 1.使用Arrays.stream将int[]转换成IntStream。
        // 2.使用IntStream中的boxed()装箱。将IntStream转换成Stream<Integer>。
        // 3.使用Stream的collect()，将Stream<T>转换成List<T>，因此正是List<Integer>。
        List<Integer> caseNumList = Arrays.stream(caseNumArray).boxed().collect(Collectors.toList());
        //模拟出现重复的情况
        for (int i = 0; i < 5; i++) {
            caseNumList.add(50);
            caseNumList.add(80);
            caseNumList.add(108);
            caseNumList.add(136);
        }

        caseNumList.sort(Collections.reverseOrder());
        //将倒叙排列的随机数List写入到新数组中
        Integer[] caseNumArrays = new Integer[caseNumList.size()];
        caseNumList.toArray(caseNumArrays);

        System.out.println("用例数量原始数据如下：\n"+Arrays.toString(caseNumArrays));
        return caseNumArrays;
    }

}


