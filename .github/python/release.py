# This uses the latest github tag pulled in order to dictate the next publish release version

import os

currentVersion = os.popen('gh release list -L 1').read().partition('\t')[0]


print('Current Version is',currentVersion)

# Increment the last value
parts = currentVersion.split('.')
parts[-1] = str(int(parts[-1]) + 1)
version = '.'.join(parts)

print('Next Version is',version)

# Check the commit list
commits = os.popen(f'git log {currentVersion}...HEAD --oneline').read().split('\n')

if(len(commits) - 1 > 0):
    print("Releasing Version",version)
    os.system(f'gh release create {version} --generate-notes')
