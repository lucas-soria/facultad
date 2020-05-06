#!/bin/bash

if [[ -e $1 ]]; then
    du -shb0BM $1
fi