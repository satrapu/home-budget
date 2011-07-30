package org.testng.internal;

import org.testng.IConfigurable;
import org.testng.IExecutionListener;
import org.testng.IHookable;
import org.testng.ITestObjectFactory;
import org.testng.internal.annotations.IAnnotationFinder;

import java.util.List;

public interface IConfiguration {
  IAnnotationFinder getAnnotationFinder();

  ITestObjectFactory getObjectFactory();
  void setObjectFactory(ITestObjectFactory m_objectFactory);

  IHookable getHookable();
  void setHookable(IHookable h);

  IConfigurable getConfigurable();
  void setConfigurable(IConfigurable c);

  List<IExecutionListener> getExecutionListeners();
  void addExecutionListener(IExecutionListener l);
}
