package org.example.spring.session.demo.extension;

import io.undertow.servlet.ServletExtension;
import io.undertow.servlet.api.DeploymentInfo;
import javax.servlet.ServletContext;

public class DisableCodedSessionConfigExtension implements ServletExtension {
    @Override
    public void handleDeployment(DeploymentInfo deploymentInfo, ServletContext servletContext) {
        deploymentInfo.setSessionConfigWrapper(null);  // to disable CodecSessionConfigWrapper set in org.wildfly.extension.undertow.deployment.UndertowDeploymentInfoService#handleDistributable method
    }
}
