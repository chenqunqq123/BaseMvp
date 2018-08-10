package com.ancely.rxjava.permission;

import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.ancely.rxjava.permission.annotation.NeedPermission;
import com.ancely.rxjava.permission.annotation.PermissionCanceled;
import com.ancely.rxjava.permission.annotation.PermissionDenied;
import com.ancely.rxjava.permission.bean.CancelBean;
import com.ancely.rxjava.permission.bean.DenyBean;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
public class PermissionAspect {

    private static final String PERMISSION_REQUEST_POINTCUT =
            "execution(@com.ancely.rxjava.permission.annotation.NeedPermission * *(..))";

    @Pointcut(PERMISSION_REQUEST_POINTCUT + " && @annotation(needPermission)")
    public void requestPermissionMethod(NeedPermission needPermission) {
    }


    @After("execution(public * mvp.ancely.com.myapplication.MainActivity.*(..))")//拦截MainActivity下所有的方法
    public void afterLog() {
        //System.out.println("After-HttpAspect: 拦截所有方法");
        Log.i("PermissionAspect","After-HttpAspect: 拦截所有方法");
    }

    @Around("requestPermissionMethod(needPermission)")
    public void AroundJoinPoint(final ProceedingJoinPoint joinPoint, final NeedPermission needPermission) {

        Context context = null;
        final Object object = joinPoint.getThis();
        if (object instanceof Context) {
            context = (Context) object;
        } else if (object instanceof Fragment) {
            context = ((Fragment) object).getActivity();
        } else if (object instanceof android.support.v4.app.Fragment) {
            context = ((android.support.v4.app.Fragment) object).getActivity();
        }
        if (context == null || needPermission == null) return;

        PermissionRequestActivity.PermissionRequest(context, needPermission.value(),
                needPermission.requestCode(),needPermission.permission(), new IPermission() {
                    @Override
                    public void PermissionGranted() {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void PermissionDenied(int requestCode, List<String> denyList) {
                        Class<?> cls = object.getClass();
                        Method[] methods = cls.getDeclaredMethods();
                        if (methods == null || methods.length == 0) return;
                        for (Method method : methods) {
                            //过滤不含自定义注解PermissionDenied的方法
                            boolean isHasAnnotation = method.isAnnotationPresent(PermissionDenied.class);
                            if (isHasAnnotation) {
                                method.setAccessible(true);
                                //获取方法类型
                                Class<?>[] types = method.getParameterTypes();
                                if (types == null || types.length != 1) return;
                                //获取方法上的注解
                                PermissionDenied aInfo = method.getAnnotation(PermissionDenied.class);
                                if (aInfo == null) return;
                                //解析注解上对应的信息
                                DenyBean bean = new DenyBean();
                                bean.setRequestCode(requestCode);
                                bean.setDenyList(denyList);
                                bean.setDenyListName(Arrays.asList(needPermission.permission()));
                                try {
                                    method.invoke(object, bean);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void PermissionCanceled(int requestCode) {
                        Class<?> cls = object.getClass();
                        Method[] methods = cls.getDeclaredMethods();
                        if (methods == null || methods.length == 0) return;
                        for (Method method : methods) {
                            //过滤不含自定义注解PermissionCanceled的方法
                            boolean isHasAnnotation = method.isAnnotationPresent(PermissionCanceled.class);
                            if (isHasAnnotation) {
                                method.setAccessible(true);
                                //获取方法类型
                                Class<?>[] types = method.getParameterTypes();
                                if (types == null || types.length != 1) return;
                                //获取方法上的注解
                                PermissionCanceled aInfo = method.getAnnotation(PermissionCanceled.class);
                                if (aInfo == null) return;
                                //解析注解上对应的信息
                                CancelBean bean = new CancelBean();
                                bean.setRequestCode(requestCode);
                                try {
                                    method.invoke(object, bean);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

}