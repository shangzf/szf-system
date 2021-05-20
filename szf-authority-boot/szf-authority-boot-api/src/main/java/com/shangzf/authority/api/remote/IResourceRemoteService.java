package com.shangzf.authority.api.remote;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "szf-authority-boot",path = "/resource")
public interface IResourceRemoteService {
}
