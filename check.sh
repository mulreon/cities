#!/bin/bash

get_latest_release() {
  curl --silent "https://api.github.com/repos/$1/releases/latest" | # Get latest release from GitHub api
    grep '"tag_name":' |                                            # Get tag line
    sed -E 's/.*"([^"]+)".*/\1/'                                    # Pluck JSON value
}

loom_version=$(get_latest_release "FabricMC/fabric-loom")
echo "FabricMC/fabric-loom = $loom_version-SNAPSHOT"

loader_version=$(get_latest_release "FabricMC/fabric-loader")
echo "FabricMC/fabric-loader = $loader_version"

api_version=$(get_latest_release "FabricMC/fabric")
echo "FabricMC/fabric = $api_version"

echo "https://fabricmc.net/develop/"