package com.bugzai.common.enums;

import org.springframework.util.StringUtils;

/**
 * @Title: condCodeEnum.java
 * @Package com.bugzai.common.enums
 * @Description: (用一句话描述该文件做什么)
 * @Date: 2020/7/6 14:17
 * @Version V1.0
 */
public enum CondCodeEnum {
    code_100("100","晴","100.png"),
    code_101("101","多云","101.png"),
    code_102("102","少云","102.png"),
    code_103("103","晴间多云","103.png"),
    code_104("104","阴","104.png"),
    code_200("200","有风","200.png"),
    code_201("201","平静","201.png"),
    code_202("202","微风","202.png"),
    code_203("203","和风","203.png"),
    code_204("204","清风","204.png"),
    code_205("205","强风/劲风","205.png"),
    code_206("206","疾风","206.png"),
    code_207("207","大风","207.png"),
    code_208("208","烈风","208.png"),
    code_209("209","风暴","209.png"),
    code_210("210","狂爆风","210.png"),
    code_211("211","飓风","211.png"),
    code_212("212","龙卷风","212.png"),
    code_213("213","热带风暴","213.png"),
    code_300("300","阵雨","300.png"),
    code_301("301","强阵雨","301.png"),
    code_302("302","雷阵雨","302.png"),
    code_303("303","强雷阵雨","303.png"),
    code_304("304","雷阵雨伴有冰雹","304.png"),
    code_305("305","小雨","305.png"),
    code_306("306","中雨","306.png"),
    code_307("307","大雨","307.png"),
    code_308("308","极端降雨","308.png"),
    code_309("309","毛毛雨/细雨","309.png"),
    code_310("310","暴雨","310.png"),
    code_311("311","大暴雨","311.png"),
    code_312("312","特大暴雨","312.png"),
    code_313("313","冻雨","313.png"),
    code_314("314","小到中雨","314.png"),
    code_315("315","中到大雨","315.png"),
    code_316("316","大到暴雨","316.png"),
    code_317("317","暴雨到大暴雨","317.png"),
    code_318("318","大暴雨到特大暴雨","318.png"),
    code_399("399","雨","399.png"),
    code_400("400","小雪","400.png"),
    code_401("401","中雪","401.png"),
    code_402("402","大雪","402.png"),
    code_403("403","暴雪","403.png"),
    code_404("404","雨夹雪","404.png"),
    code_405("405","雨雪天气","405.png"),
    code_406("406","阵雨夹雪","406.png"),
    code_407("407","阵雪","407.png"),
    code_408("408","小到中雪","408.png"),
    code_409("409","中到大雪","409.png"),
    code_410("410","大到暴雪","410.png"),
    code_499("499","雪","499.png"),
    code_500("500","薄雾","500.png"),
    code_501("501","雾","501.png"),
    code_502("502","霾","502.png"),
    code_503("503","扬沙","503.png"),
    code_504("504","浮尘","504.png"),
    code_507("507","沙尘暴","507.png"),
    code_508("508","强沙尘暴","508.png"),
    code_509("509","浓雾","509.png"),
    code_510("510","强浓雾","510.png"),
    code_511("511","中度霾","511.png"),
    code_512("512","重度霾","512.png"),
    code_513("513","严重霾","513.png"),
    code_514("514","大雾","514.png"),
    code_515("515","特强浓雾","515.png"),
    code_900("900","热","900.png"),
    code_901("901","冷","901.png"),
    code_999("999","未知","999.png");


    private String code;
    private String desc;
    private String icon;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getIcon() {
        return icon;
    }


     CondCodeEnum(String code, String desc, String icon) {
        this.code = code;
        this.desc = desc;
        this.icon = icon;
    }

    public static CondCodeEnum getEnumByCode(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }

        for (CondCodeEnum item:CondCodeEnum.values()){
            if(item.code.equals(code)){
                return item;
            }
        }
        return null;
    }

}




