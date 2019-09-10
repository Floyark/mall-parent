package com.util;

import com.response.ServerResponse;
import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

@Data
public class ResponseUtil {

    /**
     * 返回成功结果 并传递信息msg
     * @param msg
     * @return
     */
    public static ServerResponse successWithMsg(String msg){
        return new ServerResponse(200,msg,null);
    }
    /**
     * 返回成功结果 并传递数据。
     * @param o
     * @return
     */
    public static ServerResponse successWithData(Object o){
        return new ServerResponse(200,"success",o);
    }

    /**
     * 返回成功结果 没有数据传输。
     * @return
     */
    public static ServerResponse success(){
        return ResponseUtil.successWithData(null);
    }

    /**
     * 返回失败结果  msg 为失败的原因。
     * @param msg
     * @return
     */
    public static ServerResponse error(String msg,Integer code){
        return new ServerResponse(code,msg,null);
    }
}
