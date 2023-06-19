import { BasicHtmlHead } from '@/components/layout/BasicHtmlHeader'
import { HeroProducts } from './HeroProducts'
import { Header } from '@/components/layout/Header'
import { Footer } from '@/components/layout/Footer'
import { HeroImage } from '@/components/page/Top/HeroImage'

export const TopPage = () => {
  return (
    <>
      <BasicHtmlHead title="HOME e-she" description="e-she Home screen" />
      <Header />
      <HeroImage />
      <HeroProducts />
      <Footer />
    </>
  )
}
