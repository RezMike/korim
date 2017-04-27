package com.soywiz.korim.vector

import com.soywiz.korim.bitmap.Bitmap32
import com.soywiz.korim.bitmap.NativeImage
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmap
import com.soywiz.korim.vector.format.SVG
import com.soywiz.korio.async.EventLoopTest
import com.soywiz.korio.async.sync
import com.soywiz.korio.vfs.ResourcesVfs
import org.junit.Assert
import org.junit.Test

class Context2dTest {
	init {
		//System.setProperty("java.awt.headless", "true");
	}

	@Test
	fun testPropertiesAndKeep() {
		val ctx = Context2d(Context2d.Renderer.DUMMY)
		ctx.apply {
			keep {
				lineWidth = 22.0
				keep {
					keep {
						lineWidth = 33.0
						Assert.assertEquals(33.0, state.lineWidth, 0.0001)
						Assert.assertEquals(33.0, lineWidth, 0.0001)
					}
				}
				Assert.assertEquals(22.0, state.lineWidth, 0.0001)
				lineWidth = 11.0
				Assert.assertEquals(11.0, state.lineWidth, 0.0001)
			}
		}
	}

	@Test
	fun name2(): Unit = sync(EventLoopTest()) {
		val img = NativeImage(256, 256)
		val ctx = img.getContext2d()
		ctx.apply {
			keep {
				keep {
					scale(2.0, 2.0)
					rect(50.0, 20.0, 70.0, 70.0)
					clip()

					beginPath()
					moveTo(20, 20);               // Create a starting point
					lineTo(100, 20);              // Create a horizontal line
					//ctx.arcTo(150, 20, 150, 70, 50);  // Create an arc
					lineTo(150, 120);             // Continue with vertical line
					fill();                     // Draw it
				}

				fillStyle = Context2d.Color(Colors.GREEN)
				fillRect(0.0, 0.0, 50.0, 50.0)

				beginPath()
				fillStyle = Context2d.Color(Colors.GREEN)
				lineWidth = 10.0
				lineCap = Context2d.LineCap.ROUND
				moveTo(100.0, 100.0)
				lineTo(120, 120)
				rect(20.0, 20.0, 100.0, 100.0)
				stroke()
			}
		}
		//showImageAndWait(img)
		//LocalVfs("c:/temp/c2dactual.png").writeBitmap(img.toBmp32())
		//LocalVfs("c:/temp/c2dreference.png").writeBitmap(ResourcesVfs["c2dreference.png"].readBitmap().toBMP32())
		//img.toBmp32()
		Assert.assertTrue(
			Bitmap32.matches(
				ResourcesVfs["c2dreference.png"].readBitmap().toBMP32().depremultiplied(),
				img.toBmp32().depremultiplied()
			)
		)

		//LocalVfs("/tmp/file.png").writeBitmap(img.toBmp32())
		//awtShowImageAndWait(img)
	}

	@Test
	fun name3(): Unit = sync(EventLoopTest()) {
		val img = NativeImage(400, 450)
		val ctx = img.getContext2d()

		//language=xml
		/*
		val svg = SVG("""
			<svg xmlns="http://www.w3.org/2000/svg" version="1.1">
				<rect x="25" y="25" width="200" height="200" fill="lime" stroke-width="4" stroke="pink" />
				<circle cx="125" cy="125" r="75" fill="orange" />
				<polyline points="50,150 50,200 200,200 200,100" stroke="red" stroke-width="4" fill="none" />
				<line x1="50" y1="50" x2="200" y2="200" stroke="blue" stroke-width="4" />
			</svg>
		""")
		*/
		val svg = SVG("""
			<svg height="400" width="450">
			  <path id="lineAB" d="M 100 350 l 150 -300" stroke="red" stroke-width="3" fill="none" />
			  <path id="lineBC" d="M 250 50 l 150 300" stroke="red" stroke-width="3" fill="none" />
			  <path d="M 175 200 l 150 0" stroke="green" stroke-width="3" fill="none" />
			  <path d="M 100 350 q 150 -300 300 0" stroke="blue" stroke-width="5" fill="none" />
			  <!-- Mark relevant points -->
			  <g stroke="black" stroke-width="3" fill="black">
				<circle id="pointA" cx="100" cy="350" r="3" />
				<circle id="pointB" cx="250" cy="50" r="3" />
				<circle id="pointC" cx="400" cy="350" r="3" />
			  </g>
			  <!-- Label the points -->
			  <g font-size="30" font-family="sans-serif" fill="black" stroke="none" text-anchor="middle">
				<text x="100" y="350" dx="-30">A</text>
				<text x="250" y="50" dy="-10">B</text>
				<text x="400" y="350" dx="30">C</text>
			  </g>
			  Sorry, your browser does not support inline SVG.
			</svg>
		""")

		ctx.draw(svg)

		//awtShowImageAndWait(img)
	}
}