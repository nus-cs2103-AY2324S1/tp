package seedu.address.testutil;

import seedu.address.model.module.Module;

/**
 * Creates a {@code ModuleBuilder} with the default details.
 */
public class ModuleBuilder {
    private static String moduleCode;

    /**
     * Creates a {@code ModuleBuilder} with the default details.
     */
    public ModuleBuilder() {
        moduleCode = "CS2100";
    }

    public ModuleBuilder(Module module) {
        this.moduleCode = module.getModuleCode();
    }

    public Module build() {
        return new Module(moduleCode);
    }

    public Module build(String moduleCode) {
        return new Module(moduleCode);
    }
}
