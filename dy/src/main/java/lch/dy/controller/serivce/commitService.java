package lch.dy.controller.serivce;

import lch.dy.controller.Entity.commit;
import lch.dy.controller.mapper.commitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commitService {

    @Autowired
    private commitMapper CommitMapper;

    public List<commit> queryById(String start,String end){

        return CommitMapper.queryById(start,end);
    }
    public void insertCommit(String ip,String commit){
        CommitMapper.insertCommit(ip,commit);
    }
    public List<commit> queryByIndex(int index){
        return CommitMapper.queryByIndex(index);
    }
}
