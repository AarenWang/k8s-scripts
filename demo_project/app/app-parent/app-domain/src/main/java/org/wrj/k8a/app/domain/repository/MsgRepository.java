package org.wrj.k8a.app.domain.repository;

import org.wrj.k8a.app.domain.entity.Msg;

public interface MsgRepository {

    void  create(Msg msg);


    void  update(Msg msg);
}
