package cn.dyg.templatepattern.domain.staff;

import lombok.Data;

/**
 * @program: HttpClient->DevelopmentStaffAssessmentDto
 * @description: 开发部门员工考核信息
 * @author: dongyinggang
 * @create: 2020-04-16 14:11
 **/
@Data
public class DevelopmentStaffAssessmentDto extends StaffAssessmentDto{

    /**
     * 开发返工率
     */
    private String reworkRate;

    /**
     * 开发返工率等级
     */
    private String reworkRateGrade;

    /**
     * 开发返工率等级分数
     */
    private String reworkRateScore;

    /**
     * 任务按时完成率
     */
    private String taskCompletionRate;

    /**
     * 任务按时完成率等级
     */
    private String taskCompletionRateGrade;

    /**
     * 任务按时完成率等级分数
     */
    private String taskCompletionRateScore;
}
