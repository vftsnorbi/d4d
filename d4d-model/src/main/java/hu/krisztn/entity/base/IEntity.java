package hu.krisztn.entity.base;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Norbert Kriszt (norbert.kriszt@gmail.com) on 2018. 08. 03..
 */
public interface IEntity extends Serializable {

    UUID getId();

    String getKey();
}
