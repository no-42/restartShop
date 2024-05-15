package org.restart.shop.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

/**
 * HexBytesUtils - JSON
 */
public final class JsonUtils {

    private static final Log logger = LogFactory.getLog(JsonUtils.class);

    /**
     * ObjectMapper
     */
    // private static ObjectMapper mapper = new ObjectMapper()
    // .setSerializationInclusion(Include.NON_NULL);
    private static final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(Include.NON_NULL)
            .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(MapperFeature.USE_ANNOTATIONS);

    /**
     * 不可实例化
     */
    private JsonUtils() {

        // mapper.setSerializationInclusion(Include.NON_NULL);
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param value 对象
     *
     * @return JSOn字符串
     */
    public static String toJson(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            logger.error("ERROR:", e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json      JSON字符串
     * @param valueType 对象类型
     *
     * @return 对象
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json,"json为空");
        Assert.notNull(valueType,"对象类型为空");
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            logger.error("ERROR:", e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象,打印异常，误认为是异常，
     *
     * @param json      JSON字符串
     * @param valueType 对象类型
     *
     * @return 对象
     */
    public static <T> T toObjectNoPrintException(String json, Class<T> valueType) {
        Assert.hasText(json,"json为空");
        Assert.notNull(valueType,"对象类型为空");
        try {
            return mapper.readValue(json, valueType);
        } catch (Exception e) {
            //logger.error("ERROR:", e);
        }
        return null;
    }


    /**
     * 将JSON字符串转换为对象
     *
     * @param json          JSON字符串
     * @param typeReference 对象类型
     *
     * @return 对象
     */
    public static <T> T toObject(String json, TypeReference<?> typeReference) {
        Assert.hasText(json,"json为空");
        Assert.notNull(typeReference,"对象类型为空");
        try {
            return (T) mapper.readValue(json, typeReference);
        } catch (Exception e) {
            logger.error("ERROR:", e);
        }
        return null;
    }

    /**
     * 将JSON字符串转换为对象
     *
     * @param json     JSON字符串
     * @param javaType 对象类型
     *
     * @return 对象
     */
    public static <T> T toObject(String json, JavaType javaType) {
        Assert.hasText(json,"json为空");
        Assert.notNull(javaType,"对象类型为空");
        try {
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            logger.error("ERROR:", e);
        }
        return null;
    }

    /**
     * 将对象转换为JSON流
     *
     * @param writer writer
     * @param value  对象
     */
    public static void writeValue(Writer writer, Object value) {
        try {
            mapper.writeValue(writer, value);
        } catch (IOException e) {
            logger.error("ERROR:", e);
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass,
            Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass,
                elementClasses);
    }

    /**
     * 节点
     *
     * @param json
     *
     * @return
     */
    public static JsonNode toJsonNode(String json) {
        try {
            if (json != null) {
                return mapper.readTree(json);
            } else {
                return null;
            }

        } catch (Exception e) {
            logger.error("ERROR:", e);
        }
        return null;
    }
}
