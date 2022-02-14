package com.example.converge.note.androidadvanced.opengl;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FirstRender implements GLSurfaceView.Renderer {
    // 每个顶点分量的个数，这里是2，也可以用3个分量来表示一个顶点
    private static final int POSITOIN_COMPONENT_COUNT = 2;
    private static final int BYTES_PER_FLOAT = 4;
    private static final String U_COLOR = "u_Color";
    private static final String A_POSITION = "a_Position";
    private static final float color_grey[] = { 0.5f, 0.5f, 0.5f, 1.0f };
    private static final float color_red[] = { 1.0f, 0.0f, 0.0f, 1.0f };
    private float[] tableVerticesWithTriangles = {
            // 第一个三角形
            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,
            // 第二个三角形
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,
            // 直线
            -0.5f, 0f,
            0.5f, 0f,
            // 两个点
            0f, -0.25f,
            0f, 0.25f};
    // 顶点着色器代码
    private final String vertexShaderCode =
            "attribute vec4 a_Position;" +
                    "void main() {" +
                    "  gl_Position = a_Position;" +
                    "  gl_PointSize = 10.0;" +
                    "}";
    // 片元着色器代码
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 u_Color;" +
                    "void main() {" +
                    "  gl_FragColor = u_Color;" +
                    "}";
    private FloatBuffer vertexData;
    private int mPrograme;
    private int uColorLocation;
    private int aPosition;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0f,0,0,0f);
        // 1. 为顶点数据分配内存
        vertexData = ByteBuffer.allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
        // 2. 编译着色器
        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderCode);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderCode);
        // 3. 链接到 OpenGL 程序
        mPrograme = ShaderHelper.linkPrograme(vertexShader, fragmentShader);
        // 4. 验证 OpenGL 程序对象
        ShaderHelper.validatePrograme(mPrograme);
        // 5. 使用 OpenGL 程序，告诉 OpenGL 在绘制任何东西到屏幕上的时候要使用这里定义的程序
        GLES20.glUseProgram(mPrograme);
        // 6. 获取数据
        aPosition = GLES20.glGetAttribLocation(mPrograme, A_POSITION);
        uColorLocation = GLES20.glGetUniformLocation(mPrograme, U_COLOR);
        // 7. 关联属性与顶点数据的数组
        // 把当前位置设置在数组的开头
        vertexData.position(0);
        // 告诉 OpenGL，可以在 vertexData 中找到 a_Position 定义的数据
        GLES20.glVertexAttribPointer(aPosition, POSITOIN_COMPONENT_COUNT, GLES20.GL_FLOAT,
                false, 0, vertexData);
        // 8. 使能顶点数组
        GLES20.glEnableVertexAttribArray(aPosition);
    }
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        // 6.开始绘制图形
        // 6.1 更新颜色，画正方形
        GLES20.glUniform4f(uColorLocation, 0.5f, 0.5f, 0.5f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0 , 6);
        // 6.2 更新颜色，画直线
        GLES20.glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_LINES, 6 , 2);
        // 6.3 更新颜色，画点，颜色为蓝色
        GLES20.glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 8 , 1);
        // 6.4 更新颜色，画点，颜色为红色，这里用另外一个api glUniform4fv 指定Uniform变量的值
        GLES20.glUniform4fv(uColorLocation, 1, color_red, 0);
        GLES20.glDrawArrays(GLES20.GL_POINTS, 9 , 1);
    }
}