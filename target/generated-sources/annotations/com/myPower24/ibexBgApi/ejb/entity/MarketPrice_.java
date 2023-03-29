package com.myPower24.ibexBgApi.ejb.entity;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-03-29T10:47:13", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(MarketPrice.class)
public class MarketPrice_ { 

    public static volatile SingularAttribute<MarketPrice, Double> volume;
    public static volatile SingularAttribute<MarketPrice, Date> period;
    public static volatile SingularAttribute<MarketPrice, Double> priceEuro;
    public static volatile SingularAttribute<MarketPrice, Integer> id;

}