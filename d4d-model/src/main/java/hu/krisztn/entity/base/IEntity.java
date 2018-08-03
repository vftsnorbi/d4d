package hu.krisztn.entity.base;

import java.io.Serializable;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
public interface IEntity extends Serializable {

    Long getId();

    String getKey();
}
