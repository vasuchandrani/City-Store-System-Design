import { motion } from 'framer-motion';
import { 
  UserPlus, MapPin, ShoppingBag, Truck, CheckCircle,
  Store, Package, Bell, ClipboardCheck
} from 'lucide-react';

const customerSteps = [
  {
    icon: UserPlus,
    title: 'Sign Up & Select City',
    description: 'Create your account and choose your city to view local shops.',
  },
  {
    icon: ShoppingBag,
    title: 'Browse & Add to Cart',
    description: 'Explore products from multiple shops and add them to your cart.',
  },
  {
    icon: Truck,
    title: 'Checkout & Track',
    description: 'Complete your order and track delivery in real-time.',
  },
];

const shopkeeperSteps = [
  {
    icon: Store,
    title: 'Register Your Shop',
    description: 'Subscribe and complete verification to join the platform.',
  },
  {
    icon: Package,
    title: 'Add Products',
    description: 'Upload your inventory with images, prices, and descriptions.',
  },
  {
    icon: Bell,
    title: 'Receive Orders',
    description: 'Get instant notifications when customers place orders.',
  },
  {
    icon: ClipboardCheck,
    title: 'Pack & Earn',
    description: 'Pack orders and mark them ready - delivery is handled for you.',
  },
];

const deliverySteps = [
  {
    icon: UserPlus,
    title: 'Join as Partner',
    description: 'Register with your vehicle details and start earning.',
  },
  {
    icon: MapPin,
    title: 'Go Online',
    description: 'Set your availability and receive live delivery requests.',
  },
  {
    icon: Truck,
    title: 'Accept & Deliver',
    description: 'First to accept gets the order. Pick up and deliver to earn.',
  },
];

interface StepCardProps {
  step: typeof customerSteps[0];
  index: number;
  total: number;
}

const StepCard = ({ step, index, total }: StepCardProps) => {
  const Icon = step.icon;
  return (
    <motion.div
      initial={{ opacity: 0, y: 20 }}
      whileInView={{ opacity: 1, y: 0 }}
      viewport={{ once: true }}
      transition={{ delay: index * 0.15 }}
      className="relative flex flex-col items-center text-center"
    >
      {/* Connector Line */}
      {index < total - 1 && (
        <div className="hidden md:block absolute top-10 left-1/2 w-full h-0.5 bg-border" />
      )}
      
      {/* Step Number */}
      <div className="relative z-10 w-20 h-20 rounded-2xl bg-gradient-brand flex items-center justify-center mb-4 shadow-brand">
        <Icon className="w-8 h-8 text-white" />
      </div>
      
      <span className="text-sm font-bold text-brand mb-2">Step {index + 1}</span>
      <h3 className="font-bold text-lg mb-2">{step.title}</h3>
      <p className="text-sm text-muted-foreground max-w-xs">{step.description}</p>
    </motion.div>
  );
};

export function HowItWorksSection() {
  return (
    <section id="how-it-works" className="py-20 md:py-32 bg-secondary/30">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          viewport={{ once: true }}
          className="text-center mb-16"
        >
          <span className="badge-brand mb-4">How It Works</span>
          <h2 className="text-3xl md:text-4xl font-bold mb-4">
            Simple Steps to Get Started
          </h2>
          <p className="text-lg text-muted-foreground max-w-2xl mx-auto">
            Whether you're a customer, shopkeeper, or delivery partner, 
            getting started with CityShop is quick and easy.
          </p>
        </motion.div>

        {/* Customer Flow */}
        <div className="mb-20">
          <h3 className="text-xl font-bold text-center mb-10">For Customers</h3>
          <div className="grid md:grid-cols-3 gap-8">
            {customerSteps.map((step, index) => (
              <StepCard key={step.title} step={step} index={index} total={customerSteps.length} />
            ))}
          </div>
        </div>

        {/* Shopkeeper Flow */}
        <div className="mb-20">
          <h3 className="text-xl font-bold text-center mb-10">For Shopkeepers</h3>
          <div className="grid md:grid-cols-4 gap-8">
            {shopkeeperSteps.map((step, index) => (
              <StepCard key={step.title} step={step} index={index} total={shopkeeperSteps.length} />
            ))}
          </div>
        </div>

        {/* Delivery Partner Flow */}
        <div id="for-delivery">
          <h3 className="text-xl font-bold text-center mb-10">For Delivery Partners</h3>
          <div className="grid md:grid-cols-3 gap-8">
            {deliverySteps.map((step, index) => (
              <StepCard key={step.title} step={step} index={index} total={deliverySteps.length} />
            ))}
          </div>
        </div>
      </div>
    </section>
  );
}
