package com.example.wusiqi.controller;

import com.example.wusiqi.entity.User;
import com.example.wusiqi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 1. 查全部
    @GetMapping("/all")
    public List<User> all() {
        return userMapper.findAll();
    }

    // 2. 分页查询（作业要求）
    @GetMapping("/page")
    public Map<String, Object> page(@RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "2") int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        List<User> list = userMapper.findPage(offset, pageSize);
        int total = userMapper.countAll();

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);       // 当前页数据
        result.put("total", total);     // 总条数
        result.put("pageNum", pageNum); // 当前页
        result.put("pageSize", pageSize); // 每页大小
        return result;
    }

    // 3. 增
    @PostMapping
    public String add(@RequestBody User user) {
        userMapper.insert(user);
        return "新增成功，生成的ID为：" + user.getId();
    }

    // 4. 改
    @PutMapping
    public String update(@RequestBody User user) {
        userMapper.update(user);
        return "修改成功";
    }

    // 5. 删
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userMapper.deleteById(id);
        return "删除成功";
    }
}
