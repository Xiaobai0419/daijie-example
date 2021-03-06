package org.daijie.mybatis.cloud.service;

import java.util.List;

import org.daijie.api.data.feign.UserInfoCloud;
import org.daijie.core.result.ModelResult;
import org.daijie.core.result.factory.ModelResultInitialFactory.Result;
import org.daijie.mybatis.mapper.UserInfoMapper;
import org.daijie.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoService implements UserInfoCloud {
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public ModelResult<UserInfo> getUserinfo(Integer userId) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		return Result.build(userInfo);
	}

	@Override
	public ModelResult<List<UserInfo>> getUserinfoAll() {
		return Result.build(userInfoMapper.selectAll());
	}

	@Override
	public ModelResult<UserInfo> getUserinfo(String mobile) {
		UserInfo userinfo = new UserInfo();
		userinfo.setMobile(mobile);
		return Result.build(userInfoMapper.selectOne(userinfo));
	}

	@Override
	public ModelResult<Boolean> updateUserinfo(UserInfo userInfo) {
		return Result.build(userInfoMapper.updateByPrimaryKey(userInfo) > 0);
	}

	@Override
	public ModelResult<Boolean> addUserinfo(UserInfo userInfo) {
		return Result.build(userInfoMapper.insert(userInfo) > 0);
	}

}
