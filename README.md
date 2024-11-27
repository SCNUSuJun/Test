苏俊@SJ-Computer MINGW64 ~/Desktop/test (master)
$ git init
Initialized empty Git repository in C:/Users/苏俊/Desktop/test/.git/

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git remote add origin git@github.com:SCNUSuJun/test.git

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git add .

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git commit -m "Merge remote changes"
On branch main

Initial commit

nothing to commit (create/copy files and use "git add" to track)

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git push -u origin main
error: src refspec main does not match any
error: failed to push some refs to 'github.com:SCNUSuJun/test.git'

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git add .

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git commit -m "Merge remote changes"
[main (root-commit) c2546df] Merge remote changes
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 test.txt

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git push -u origin main
To github.com:SCNUSuJun/test.git
 ! [rejected]        main -> main (fetch first)
error: failed to push some refs to 'github.com:SCNUSuJun/test.git'
hint: Updates were rejected because the remote contains work that you do not
hint: have locally. This is usually caused by another repository pushing to
hint: the same ref. If you want to integrate the remote changes, use
hint: 'git pull' before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git pull origin main
remote: Enumerating objects: 3, done.
remote: Counting objects: 100% (3/3), done.
remote: Total 3 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)
Unpacking objects: 100% (3/3), 860 bytes | 86.00 KiB/s, done.
From github.com:SCNUSuJun/test
 * branch            main       -> FETCH_HEAD
 * [new branch]      main       -> origin/main
fatal: refusing to merge unrelated histories

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git push -u origin main
To github.com:SCNUSuJun/test.git
 ! [rejected]        main -> main (non-fast-forward)
error: failed to push some refs to 'github.com:SCNUSuJun/test.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. If you want to integrate the remote changes,
hint: use 'git pull' before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git pull origin main --allow-unrelated-histories
From github.com:SCNUSuJun/test
 * branch            main       -> FETCH_HEAD
Merge made by the 'ort' strategy.
 README.md | 2 ++
 1 file changed, 2 insertions(+)
 create mode 100644 README.md

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git commit -m "Merge remote changes"
On branch main
nothing to commit, working tree clean

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git push -u origin main --force
Enumerating objects: 6, done.
Counting objects: 100% (6/6), done.
Delta compression using up to 16 threads
Compressing objects: 100% (3/3), done.
Writing objects: 100% (5/5), 516 bytes | 258.00 KiB/s, done.
Total 5 (delta 0), reused 0 (delta 0), pack-reused 0
To github.com:SCNUSuJun/test.git
   baa6cf4..52708e2  main -> main
branch 'main' set up to track 'origin/main'.

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git add .

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git commit -m "Merge remote changes"
[main d8fe345] Merge remote changes
 1 file changed, 1 insertion(+)
 create mode 100644 1.txt

苏俊@SJ-Computer MINGW64 ~/Desktop/test (main)
$ git push -u origin main
Enumerating objects: 4, done.
Counting objects: 100% (4/4), done.
Delta compression using up to 16 threads
Compressing objects: 100% (2/2), done.
Writing objects: 100% (3/3), 321 bytes | 321.00 KiB/s, done.
Total 3 (delta 0), reused 0 (delta 0), pack-reused 0
To github.com:SCNUSuJun/test.git
   52708e2..d8fe345  main -> main
branch 'main' set up to track 'origin/main'.
