package com.zhaotao.beanannotation.multibean;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by 陶 on 2018/12/16.
 */
@Order(1)
@Component
public class BeanImplTwo implements BeanInterface {
}
