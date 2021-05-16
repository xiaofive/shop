package com.shop.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalCheck extends JsonSerializer<BigDecimal> {
        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                if(value.compareTo(new BigDecimal(0))==0){
                    //参考该方法 第二个参数是几就保留几位小数 第三个参数 参考 RoundingMode.java
                    gen.writeNumber(new BigDecimal(0));
                }else{
                    //参考该方法 第二个参数是几就保留几位小数 第三个参数 参考 RoundingMode.java
                    gen.writeNumber(value.setScale(2, RoundingMode.HALF_UP));
                }
            }else{
                gen.writeNumber(new BigDecimal(0));
            }
        }

}
