package cn.dyg.templatepattern.domain.staff;

import lombok.Data;

/**
 * @program: HttpClient->TestStaffAssessmentDto
 * @description: 测试部门员工考核信息
 * @author: dongyinggang
 * @create: 2020-04-16 14:09
 **/
@Data
public class TestStaffAssessmentDto extends StaffAssessmentDto{
    /**
     * 用例数量
     */
    private String caseNum;

    /**
     * 用例数量等级
     */
    private String caseNumGrade;

    /**
     * 用例数量等级得分
     */
    private String caseNumScore;

    /**
     * 漏检率
     */
    private String missCheckRate;

    /**
     * 漏检率等级
     */
    private String missCheckGrade;

    /**
     * 漏检率等级得分
     */
    private String missCheckRateScore;
}
