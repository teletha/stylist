# Changelog

## [1.1.0](https://www.github.com/Teletha/stylist/compare/v1.0.1...v1.1.0) (2021-04-28)


### Features

* Add arithmetic operations i.e. Numeric#xxx(double, Unit). ([251c68c](https://www.github.com/Teletha/stylist/commit/251c68c79e5f9378245d7949bd2f4abb64a0f6d1))
* Add builtin style Style#tooltip. ([275a660](https://www.github.com/Teletha/stylist/commit/275a66036b05bbf0b95250aaf9559bac319cd4a7))
* Support CSS variable. ([8b46aea](https://www.github.com/Teletha/stylist/commit/8b46aea803bcdc0e9b6c671abc9e900abeb9f69d))
* Support list-style-type: disclosure-open and closed. ([63ae1e1](https://www.github.com/Teletha/stylist/commit/63ae1e1a18f9829299c7da66739dcc726650f99b))
* Support shorthand method for Color#Transparent. ([97ee30f](https://www.github.com/Teletha/stylist/commit/97ee30f6d71e7cb0c6d6993df356190523cf39ce))
* Support start and end position on linear-gradient. ([515a502](https://www.github.com/Teletha/stylist/commit/515a502b2b468dd72238211104cff487ae2be14c))
* Support text-decoration-style property. ([e401680](https://www.github.com/Teletha/stylist/commit/e4016803cae01fe4b798e7f3e45f56882c6853cb))
* Support transform-origin property. ([e105394](https://www.github.com/Teletha/stylist/commit/e105394c0bcb142c01f4e25166385bb088f7281d))
* Support transition property on DSL. ([492ef5b](https://www.github.com/Teletha/stylist/commit/492ef5b9fe6f156aaa9812107fa63f8a84cb6cc6))
* Support user defined theme. ([8b46aea](https://www.github.com/Teletha/stylist/commit/8b46aea803bcdc0e9b6c671abc9e900abeb9f69d))
* The align-items property Supports "first(last) baseline" and ([f6d4b02](https://www.github.com/Teletha/stylist/commit/f6d4b021b838744f53bbe7d61bb19ffc777ccd0f))
* Use font-display:swap in Google Fonts. ([489ca00](https://www.github.com/Teletha/stylist/commit/489ca00d66dfc301973cc4e596994e9e9e60aa69))


### Bug Fixes

* Make transition property resetable. ([70e391e](https://www.github.com/Teletha/stylist/commit/70e391e7c7bbc816a4f815516dbdb945f2b27716))
* The line-height property merged into font property. ([2706e3f](https://www.github.com/Teletha/stylist/commit/2706e3fdda05d656a1995b54e4a15a1167cdc957))
* The visibility property merged into display property. ([9fca1b0](https://www.github.com/Teletha/stylist/commit/9fca1b0c5b8b245a45283505e7dde32ffbc78df1))

### [1.0.1](https://www.github.com/Teletha/stylist/compare/v1.0.0...v1.0.1) (2021-03-29)


### Bug Fixes

* Document. ([96c3fd6](https://www.github.com/Teletha/stylist/commit/96c3fd600225f43298c22391bc9be490cbf2b539))

## 1.0.0 (2021-03-28)


### Features

* Enable CI. ([49f2fbc](https://www.github.com/Teletha/stylist/commit/49f2fbcb5bfb5b14760599f17075d4e1053b50e3))


### Bug Fixes

* Color can't output rgba format properly. ([279cc17](https://www.github.com/Teletha/stylist/commit/279cc17df3d178b315c52cfac94d65ed8e2726ae))
* File writing error. ([d74e3fe](https://www.github.com/Teletha/stylist/commit/d74e3fe8160189495f737de233a9bd4d0d7e7182))
* Nested selector is broken. ([4bc33de](https://www.github.com/Teletha/stylist/commit/4bc33de834f57b84420301d85fda72a81be2c2a0))
* SelectorDSL#select is broken. ([0573c19](https://www.github.com/Teletha/stylist/commit/0573c19a2b7348af905d73d6258b7db91b4838ff))
* StyleRule can't resolve combinator. ([ef8bb31](https://www.github.com/Teletha/stylist/commit/ef8bb31e906e8599575dae5cfeec19737989d836))
* Update pom. ([c6b225b](https://www.github.com/Teletha/stylist/commit/c6b225b12839c64f2d0f7f896f73c0d6090c15b2))
* Write child style if its parent is empty. ([b382831](https://www.github.com/Teletha/stylist/commit/b382831704ea143829a82d52e8a20a3be334d98e))
