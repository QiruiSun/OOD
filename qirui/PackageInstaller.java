package qirui;
import java.util.*;

/*
Use Cases:
1. can install a single package
2. can also install a list of packages
2. A package may depend on several other packages (dependencies)
3. DFS vs Topo sort?

*/

/*
// Classes:

1. Package

*/

class Package {
    private String name;
    private String src;
    private List<Package> dependencies;
    private String version;
    private boolean installed;

    public Package(String name, String src, String version, List<Package> dependencies) {
        this.name = name;
        this.src = name;
        this.version = version;
        this.dependencies = dependencies;
    }

    public String getFullName() {
        return name + "_" + version;
    }

    public boolean canInstall() {
        for (Package p : dependencies) {
            if (!p.isInstalled()) {
                return false;
            }
        }
        return true;
    }

    public List<Package> getDependencies() {
        return dependencies;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void install() {
        // download from src
        installed = true;
        return;
    }
}

public class PackageInstaller {
    private PackageInstaller INSTANCE;

    private PackageInstaller() {}

    public PackageInstaller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PackageInstaller();
        }
        return INSTANCE;
    }

    public boolean installPackages(List<Package> packages) {
        for (Package p : packages) {
            if (!dfs(p)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Package pack) {
        if (pack.isInstalled()) {
            return true;
        }
        for (Package p : pack.getDependencies()) {
            if (!dfs(p)) {
                return false;
            }
        }
        if (!pack.canInstall()) {
            return false;
        }
        pack.install();
        return true;
    }
}


