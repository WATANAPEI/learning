import { Header } from '@/components/layout/Header'
import { Footer } from '@/components/layout/Footer'
import { BasicHtmlHead } from '@/components/layout/BasicHtmlHeader'

export const CartPage = () => {
  return (
    <>
      <BasicHtmlHead
        title="Cart"
        description="You can check items in your cart"
      />
      <Header />
      <Footer />
    </>
  )
}
