package com.wms.common.util;

import com.wms.common.exception.SysException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by ThomasYu on 2019-07-25
 */
@Slf4j
public class SerializeUtils {

    // 1 KB
    private static final int buffer_size = 1024;

    /**
     * serialize
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        byte[] result;
        if (object == null) {
            return new byte[0];
        }
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(
                    SerializeUtils.class.getSimpleName() + " requires a Serializable payload "
                            + "but received an object of type [" + object.getClass().getName() + "]");
        }
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(buffer_size);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            result = byteStream.toByteArray();
        } catch (Exception ex) {
            log.error("", ex);
            throw new SysException("Failed to serialize");
        }
        return result;
    }

    /**
     * deserialize
     *
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            log.error("", e);
            throw new SysException("Failed to deserialize!");
        }
    }

}
