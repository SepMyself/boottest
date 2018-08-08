package com.example.boottest.config;

import com.example.boottest.model.ResultModel;
import com.example.boottest.util.RequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyRestExceptionHandler extends AbstractHandlerExceptionResolver {
	@Autowired
	private ObjectMapper mapper;

	@Override
	public ModelAndView doResolveException(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
//		if(true) {
			ModelAndView result = new ModelAndView();
			result.setStatus(HttpStatus.OK);
//			result.addObject()
			ResultModel model = new ResultModel();
			model.setState(HttpStatus.OK);
			String requestFormat = RequestUtil.formatRequest((BodyReaderRequestWrapper)request);
			logger.info(requestFormat);
			model.setData(3);
			response.getWriter().println(mapper.writeValueAsString(model));
			return result;
		} catch (Exception eo) {

		}
		return null;
	}
}
