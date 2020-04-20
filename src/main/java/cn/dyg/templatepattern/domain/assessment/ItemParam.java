package cn.dyg.templatepattern.domain.assessment;

import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ItemParam类（或接口）是 考核项的参数配置类
 * HttpClient->
 *
 * @author dongyinggang
 * @date 2020-04-16 17:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemParam {

    /**
     * 考核项名称
     */
    private String itemName;

    /**
     * 等级
     */
    private String grade;

    /**
     * 比例
     */
    private int percent;

    /**
     * 分数
     */
    private int score;

    /**
     * 等级人数
     */
    private int personNum;

    /**
     * setCurrentGradePersonNum方法是 根据考核项值
     *
     * @param length 考核项值的数量
     * @author dongyinggang
     * @date 2020/4/17 13:02
     */
    public void setCurrentGradePersonNum(int length) {
        this.personNum = (int) NumberUtil.div(length * this.percent, 100, 0);
    }

}
