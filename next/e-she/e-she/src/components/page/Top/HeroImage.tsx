import Image from 'next/image'
import ExplodeImage from '/public/HeroImage.webp'

export const HeroImage = () => {
  return (
    <section>
      <header>
        <h1>You can buy this and that.</h1>
      </header>
      <Image src={ExplodeImage} alt="HeroImage" />
    </section>
  )
}
