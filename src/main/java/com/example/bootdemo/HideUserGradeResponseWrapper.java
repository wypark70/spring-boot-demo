package com.example.bootdemo;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class HideUserGradeResponseWrapper extends HttpServletResponseWrapper {

  private final ByteArrayOutputStream capture;
  private ServletOutputStream output;
  private PrintWriter writer;

  public HideUserGradeResponseWrapper(HttpServletResponse response) {
    super(response);
    capture = new ByteArrayOutputStream(response.getBufferSize());
  }

  @Override
  public ServletOutputStream getOutputStream() {
    if (writer != null) {
      throw new IllegalStateException("getWriter() has already been called on this response.");
    }
    if (output == null) {
      output = new ServletOutputStream() {
        @Override
        public void write(int b) throws IOException {
          capture.write(b);
        }

        @Override
        public void flush() throws IOException {
          capture.flush();
        }

        @Override
        public void close() throws IOException {
          capture.close();
        }

        @Override
        public boolean isReady() {
          return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
          // empty
        }
      };
    }

    return output;
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    if (output != null) {
      throw new IllegalStateException("getOutputStream() has already been called on this response.");
    }
    if (writer == null) {
      writer = new PrintWriter(new OutputStreamWriter(capture, getCharacterEncoding()));
    }
    return writer;
  }

  @Override
  public void flushBuffer() throws IOException {
    super.flushBuffer();

    if (writer != null) {
      writer.flush();
    } else if (output != null) {
      output.flush();
    }
  }

  public byte[] getContentAsBytes() throws IOException {
    if (writer != null) {
      writer.close();
    } else if (output != null) {
      output.close();
    }

    return capture.toByteArray();
  }

  public String getContentAsString() throws IOException {
    return new String(getContentAsBytes(), getCharacterEncoding());
  }

}
