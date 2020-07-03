package handler;

import lombok.Data;

/**
 * @Title: HandlerMessage.java
 * @Package handler
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/3 18:11
 * @Version V1.0
 */
@Data
public class HandlerMessage {
    /**
     * 是否进行下一步
     */
    private Boolean toNext;
}
