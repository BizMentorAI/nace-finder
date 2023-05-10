save-function-list

load ~/.zsh/environments/basic.zsh

alias emacs="emacs --load $PWD/.env/emacs.el"
path-prepend $PWD/.env/bin

# _bb-tasks() {
#   bb --tasks | grep -E '^[a-z-]+ +#' | awk -F# '{ print $1 }'
# }

# # Proxy all Babashka tasks as a shell alias.
# for task in $(_bb-tasks); do
#   alias $task="bb $task"
# done

# TODO: Proxy all.
alias et="./.env/bin/et.clj"

export ET_JAR=~/Documents/Projects/et/target/et.jar

function recreate-wip-branch() {
  # TODO: ask whether you merged in first, Y/n.
  if test wip = $(git branch | grep -E '^\*' | cut -c 3-); then
    git stash
    git checkout rel
    git pull -r
    git branch -D wip
    git co -b wip
    git stash pop
  fi
}

report-custom-functions
