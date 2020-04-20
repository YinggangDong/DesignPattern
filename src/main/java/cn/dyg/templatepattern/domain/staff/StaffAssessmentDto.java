package cn.dyg.templatepattern.domain.staff;

import lombok.Data;

/**
 * @program: HttpClient->StaffAssessmentDto
 * @description: 员工考核信息
 * @author: dongyinggang
 * @create: 2020-04-16 13:40
 **/
@Data
public class StaffAssessmentDto {

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门
     */
    private String department;

    /**
     * 地区
     */
    private String region;

    /**
     * 最终考核得分
     */
    private String assessmentScore;

    /**
     * 最终考核等级
     */
    private String assessmentGrade;

}
