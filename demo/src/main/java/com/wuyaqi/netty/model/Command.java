package com.wuyaqi.netty.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yaqi.wu
 * 2019-11-17 12:50
 */
@Data
public class Command implements Serializable {

    private String actionName;

}
