# This uses the latest github tag pulled in order to dictate the next publish release version

import os

currentVersion = os.popen('git describe --tags --abbrev=0').read().split('\n')[0]

# Increment the last value
parts = currentVersion.split('.')
parts[-1] = str(int(parts[-1]) + 1)
version = '.'.join(parts)

# Check the commit list
commits = os.popen(f'git log {currentVersion}...HEAD --oneline').read().split('\n')
print(len(commits) - 1)

if(len(commits) - 1 > 0):
    print("Releasing Version",version)
    os.system(f'gh release create {version} --generate-notes')
