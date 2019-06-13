package com.zoomdu.tour.guide.support.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.endofmaster.rest.exception.ServerException;

/**
 * @author ZM.Wang
 */
public class AliyunSms {

    private final IAcsClient acsClient;

    public AliyunSms(String accessKeyId, String accessKeySecret) throws com.aliyuncs.exceptions.ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-shenzhen", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-shenzhen", "cn-shenzhen", "Dysmsapi", "dysmsapi.aliyuncs.com");
        this.acsClient = new DefaultAcsClient(profile);
    }

    /** 发送短信验证码 */
    public void sendSmsResponse(String phone, String code) {

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("找导游");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_119770039");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //hint 此处可能会抛出异常，注意catch
        try {
            acsClient.getAcsResponse(request);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            throw new ServerException("发送短信错误", e);
        }
    }

    /** 发送给导游提醒确认接单 */
    public void sendSmsToNewOrderOfGuideResponse(String phone) {

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("找导游");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_148861018");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //hint 此处可能会抛出异常，注意catch
        try {
            acsClient.getAcsResponse(request);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            throw new ServerException("发送短信错误", e);
        }
    }

    /** 发送给用户取消订单 因导游不接单而自动取消订单 */
    public void sendSmsToCancelOfUserResponse(String phone, String date) {

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("找导游");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_148865974");
        request.setTemplateParam("{\"date\":\"" + date + "\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //hint 此处可能会抛出异常，注意catch
        try {
            acsClient.getAcsResponse(request);
        } catch (com.aliyuncs.exceptions.ClientException e) {
            throw new ServerException("发送短信错误", e);
        }
    }


}
