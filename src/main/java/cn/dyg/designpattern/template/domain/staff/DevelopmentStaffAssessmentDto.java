package cn.dyg.designpattern.template.domain.staff;

import lombok.Data;

/**
 * DevelopmentStaffAssessmentDto 类（或接口）是 开发部门员工考核信息
 *
 * @author dongyinggang
 * @date 2020/4/20
 */
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
