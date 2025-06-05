package com.wg.gichess.chess.rolechess;


import com.wg.gichess.chess.Coord;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.vfs.Vfs;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class RoleFactoryV2 {
    public static final Map<String, Class<? extends RoleChess>> roleClasses = new LinkedHashMap<>();

    static {
        // 初始化 VFS 以支持 NeoForge 特殊路径
        initVfsForNeoForge();

        // 自动注册角色
        autoRegisterRoles();

        // 调试输出
        System.out.println("[RoleFactory] 已注册角色数量: " + roleClasses.size());
    }

    /**
     * 初始化 VFS 以支持 NeoForge 的特殊路径格式
     */
    private static void initVfsForNeoForge() {
        Vfs.addDefaultURLTypes(new Vfs.UrlType() {
            @Override
            public boolean matches(URL url) {
                // 匹配 NeoForge 的特殊路径格式
                return url.getProtocol().equals("union") ||
                        url.getFile().contains("%23") ||
                        url.getFile().contains("!/");
            }

            @Override
            public Vfs.Dir createDir(URL url) {
                try {
                    String filePath = url.getFile();

                    // 处理特殊编码路径
                    if (filePath.contains("%23")) {
                        filePath = filePath.replace("%23", "#").split("!")[0];
                    }
                    // 处理 JAR 内路径
                    else if (filePath.contains("!/")) {
                        filePath = filePath.substring("file:".length(), filePath.indexOf("!/"));
                    }

                    File file = new File(filePath);
                    if (file.exists()) {
                        return Vfs.fromURL(file.toURI().toURL());
                    }
                } catch (Exception e) {
                    System.err.println("[RoleFactory] 无法处理路径: " + url);
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /**
     * 自动扫描并注册所有带有 @AutoRegisterRole 注解的角色类
     */
    private static void autoRegisterRoles() {
        try {
            Reflections reflections = new Reflections(
                    new ConfigurationBuilder()
                            .forPackage("com.wg.gichess.chess.rolechess") // 扫描的根包
                            .setScanners(Scanners.TypesAnnotated) // 只扫描注解
                            .setClassLoaders(new ClassLoader[]{
                                    RoleFactoryV2.class.getClassLoader() // 使用当前ClassLoader
                            })
            );

            // 获取所有带注解的类
            Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(AutoRegisterRole.class);
            System.out.println("[RoleFactory] 扫描到候选类: " + annotatedClasses.size());

            for (Class<?> clazz : annotatedClasses) {
                // 确保是 RoleChess 的子类且不是抽象类
                if (RoleChess.class.isAssignableFrom(clazz) && !clazz.isInterface()
                        && !Modifier.isAbstract(clazz.getModifiers())) {

                    AutoRegisterRole annotation = clazz.getAnnotation(AutoRegisterRole.class);
                    @SuppressWarnings("unchecked")
                    Class<? extends RoleChess> roleClass = (Class<? extends RoleChess>) clazz;

                    roleClasses.put(annotation.key(), roleClass);
                    System.out.println("[RoleFactory] 注册角色: " + annotation.key() + " -> " + clazz.getName());
                }
            }
        } catch (Exception e) {
            System.err.println("[RoleFactory] 自动注册失败:");
            e.printStackTrace();
        }
    }

    /**
     * 创建角色实例
     */
    public static RoleChess createRole(String key, Coord coord, int level, int constellation, int skin) {
        try {
            Class<? extends RoleChess> roleClass = roleClasses.get(key);
            if (roleClass != null) {
                return roleClass.getConstructor(Coord.class, int.class, int.class, int.class)
                        .newInstance(coord, level, constellation, skin);
            }
            System.err.println("[RoleFactory] 未找到角色: " + key);
            return null;
        } catch (Exception e) {
            System.err.println("[RoleFactory] 创建角色实例失败: " + key);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查角色是否存在
     */
    public static boolean containsRole(String key) {
        return roleClasses.containsKey(key);
    }

    /**
     * 获取所有已注册的角色key
     */
    public static Iterable<String> getRegisteredRoles() {
        return Collections.unmodifiableSet(roleClasses.keySet());
    }

    /**
     * 获取角色类
     */
    public static Class<? extends RoleChess> getRoleClass(String key) {
        return roleClasses.get(key);
    }
}