// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service1.proto

package service1;

public interface SleepPatternResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:service1.SleepPatternResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *fields of the proto. String and enumeration type
   * </pre>
   *
   * <code>string userName = 1;</code>
   */
  java.lang.String getUserName();
  /**
   * <pre>
   *fields of the proto. String and enumeration type
   * </pre>
   *
   * <code>string userName = 1;</code>
   */
  com.google.protobuf.ByteString
      getUserNameBytes();

  /**
   * <code>.service1.SleepPatternResponse.Pattern pattern = 2;</code>
   */
  int getPatternValue();
  /**
   * <code>.service1.SleepPatternResponse.Pattern pattern = 2;</code>
   */
  service1.SleepPatternResponse.Pattern getPattern();
}
