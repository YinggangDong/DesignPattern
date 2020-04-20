package cn.dyg.templatepattern.domain.assessment;

import lombok.Data;

/**
 * @program: HttpClient->AssessmentResultDto
 * @description: 考核结果对象
 * @author: dongyinggang
 * @create: 2020-04-16 14:45
 **/
@Data
public class AssessmentResultDto {

    /**
     * 考核项
     */
    private String assessmentItem;

    /**
     * 结果
     */
    private String grade;

    /**
     * 比例
     */
    private int percent;

    /**
     * 区间
     */
    private String interval;

    /**
     * 人数
     */
    private int personNum;

    /**
     * 考核分数
     */
    private int score;

    /**
     * 按格式输出考核结果
     */
    public void printResult() {
        System.out.printf("%-10s %-5s %-5s %-20s %-5s %-5s %n", assessmentItem, grade, percent + "%", interval, personNum, score);
    }

}
