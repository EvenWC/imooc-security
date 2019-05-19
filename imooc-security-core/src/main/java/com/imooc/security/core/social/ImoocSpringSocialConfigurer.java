/**
 * 
 */
package com.imooc.security.core.social;

import com.imooc.security.core.social.process.SocialAuthenticationFilterPostProcessor;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {
	
	private String filterProcessesUrl;

	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcesser;
	
	public ImoocSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		if(socialAuthenticationFilterPostProcesser != null){
			socialAuthenticationFilterPostProcesser.process(filter);
		}
		return (T) filter;
	}

	public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcesser() {
		return socialAuthenticationFilterPostProcesser;
	}

	public void setSocialAuthenticationFilterPostProcesser(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcesser) {
		this.socialAuthenticationFilterPostProcesser = socialAuthenticationFilterPostProcesser;
	}
}
