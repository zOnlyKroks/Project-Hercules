package de.zonlykroks.hercules.visitor;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Scope {

    private final Scope parent;

    private final Map<String, Object> variables = new Hashtable<>();

    private final List<String> finalVariables = new ArrayList<>();
    private final List<String> swallowingVariables = new ArrayList<>();

    private final Map<String, MethodImpl> methods = new Hashtable<>();

    public Scope(@Nullable Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {
        return this.parent;
    }

    public void addVariable(String name, @Nullable Object value) {
        this.variables.put(name,value);
    }

    public Object getVariable(@NotNull String name) {
        Object value = this.variables.getOrDefault(name, null);

        if(value == null && this.parent != null) {
            value = getParent().getVariable(name);
        }

        return value;
    }

    public void addFinalVariable(String name) {
        this.finalVariables.add(name);
    }

    public boolean isFinalVariable(String name) {
        boolean result = this.finalVariables.contains(name);

        if(!result && this.parent != null) {
            result = getParent().isFinalVariable(name);
        }

        return result;
    }

    public void addMethod(String name, MethodImpl method) {
        this.methods.put(name, method);
    }

    public MethodImpl getMethod(String name) {
        MethodImpl impl = this.methods.getOrDefault(name, null);

        if(impl == null && this.parent != null) {
            impl = getParent().getMethod(name);
        }

        return impl;
    }

    public boolean isVariableVisible(String name) {
        boolean visible = this.variables.containsKey(name);

        if(!visible && this.parent != null) {
            visible = getParent().isVariableVisible(name);
        }

        return visible;
    }

    public boolean isFunctionVisible(String name) {
        boolean visible = this.methods.containsKey(name);

        if(!visible && this.parent != null) {
            visible = getParent().isFunctionVisible(name);
        }

        return visible;
    }

    public void addSwallowingVariable(String name) {
        this.swallowingVariables.add(name);
    }

    public boolean containsSwallowingVariable(String name) {
        boolean visible = this.swallowingVariables.contains(name);

        if(!visible && this.parent != null) {
            visible = getParent().containsSwallowingVariable(name);
        }

        return visible;
    }

}
