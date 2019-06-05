<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<html>
<head>
    <title>RichFaces Greeter</title>
    <script type="text/javascript">
        function echo(request, event, data, message) {
            console.log(request);
            console.log(event);
            console.log(data);
            console.log(message);
        }
    </script>
</head>
<body>
<f:view>
    <h:form id="planetsForm">
        <h:outputLabel value="Select the planet:" for="planets" />
        <h:selectOneMenu id="planets" value="#{planetsMoons.currentPlanet}" valueChangeListener="#{planetsMoons.planetChanged}">
            <f:selectItems value="#{planetsMoons.planetsList}"/>
            <a4j:support event="onchange" reRender="moons" oncomplete="echo(request, event, data, 'hello world')"/>
            <%--
            It does not work function name for oncomplete handler on Richfaces, however in the case of JSF2, it works fine.
            There is a difference how to define JavaScript event handler between Richfaces and JSF2 Ajax(mojarra).

            <a4j:support event="onchange" reRender="moons" oncomplete="echo"/>
            --%>
        </h:selectOneMenu>

        <h:dataTable id="moons" value="#{planetsMoons.moonsList}" var="item">
            <h:column>
                <h:outputText value="#{item}"/>
            </h:column>
        </h:dataTable>
    </h:form>
</f:view>
</body>
</html>