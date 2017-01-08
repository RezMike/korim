package com.soywiz.korim.color

object RGBA_4444 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	rOffset = 0, rSize = 4,
	gOffset = 4, gSize = 4,
	bOffset = 8, bSize = 4,
	aOffset = 12, aSize = 4
)

object RGBA_5551 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	rOffset = 0, rSize = 5,
	gOffset = 5, gSize = 5,
	bOffset = 10, bSize = 5,
	aOffset = 15, aSize = 1
)

object RGBA_5550 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	rOffset = 0, rSize = 5,
	gOffset = 5, gSize = 5,
	bOffset = 10, bSize = 5,
	aOffset = 15, aSize = 0
)

object RGBA_5650 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	rOffset = 0, rSize = 5,
	gOffset = 5, gSize = 6,
	bOffset = 11, bSize = 5,
	aOffset = 15, aSize = 0
)

/////////////////////////////////////////////////////////////////////////////

object BGRA_4444 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	bOffset = 0, bSize = 4,
	gOffset = 4, gSize = 4,
	rOffset = 8, rSize = 4,
	aOffset = 12, aSize = 4
)

object BGRA_5550 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	bOffset = 0, bSize = 5,
	gOffset = 5, gSize = 5,
	rOffset = 10, rSize = 5,
	aOffset = 15, aSize = 0
)

object BGRA_5650 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	bOffset = 0, bSize = 5,
	gOffset = 5, gSize = 6,
	rOffset = 11, rSize = 5,
	aOffset = 15, aSize = 0
)

object BGRA_5551 : ColorFormat16(), ColorFormatBase by ColorFormatBase.Mixin(
	bOffset = 0, bSize = 5,
	gOffset = 5, gSize = 5,
	rOffset = 10, rSize = 5,
	aOffset = 15, aSize = 1
)